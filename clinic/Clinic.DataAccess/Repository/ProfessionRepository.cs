using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class ProfessionRepository : RepositoryAsync<Profession>, IProfessionRepository
    {
        private readonly ApplicationDbContext _db;
        public ProfessionRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(Profession profession)
        {
        }
    }
}
