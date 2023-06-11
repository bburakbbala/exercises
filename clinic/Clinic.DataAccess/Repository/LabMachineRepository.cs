using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class LabMachineRepository : RepositoryAsync<LabMachine>, ILabMachineRepository
    {
        private readonly ApplicationDbContext _db;
        public LabMachineRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(LabMachine labMachine)
        {
        }
    }
}
