using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface ILabMachineRepository : IRepositoryAsync<LabMachine>
    {
        void Update(LabMachine labMachine);
    }
}