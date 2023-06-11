using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;
using System.Linq;

namespace Clinic.DataAccess.Repository
{
    public class LabRepository : RepositoryAsync<Lab>, ILabRepository
    {
        private readonly ApplicationDbContext _db;
        public LabRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(Lab lab)
        {
            var objFromDb = _db.Labs.FirstOrDefault(d => d.Id == lab.Id);
            if (objFromDb != null)
            {
                objFromDb.Name = lab.Name;
            }
        }
    }
}
